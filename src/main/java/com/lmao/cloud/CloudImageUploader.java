package com.lmao.cloud;

import com.google.gson.Gson;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

@Component
public class CloudImageUploader {
    private static final String PICTURE_FOLDER_ID = "1695326173";

    private static final String QUERY_PATH_SEPARATOR = "?";

    private static final String QUERY_PARAMETER_SEPARATOR = "&";

    private static final String AUTH_PARAMETER = "auth=";

    private static final String FOLDER_ID_PARAMETER = "folderid=";

    private static final String FILE_NAME_PARAMETER = "filename=";

    private static final String UPLOAD_FILE_URL
            = "https://api.pcloud.com/uploadfile";


    private static final String LIST_FILE_URL
            = "https://api.pcloud.com/getfilepublink";

    private static final String FILE_ID_PARAMETER = "fileid=";

    private static final String DOWNLOAD_FILE_URL
            = "https://api.pcloud.com/getpublinkdownload";

    private static final String CODE_PARAMETER = "code=";

    private final HttpRequestExecutor httpRequestExecutor;

    private final CloudAuthorizationService cloudAuthorizationService;

    public CloudImageUploader(HttpRequestExecutor httpRequestExecutor, CloudAuthorizationService cloudAuthorizationService) {
        this.httpRequestExecutor = httpRequestExecutor;
        this.cloudAuthorizationService = cloudAuthorizationService;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        Gson gson = new Gson();
        String accessToken = this.cloudAuthorizationService.getAccessToken();

        String uploadImageResponse = this.httpRequestExecutor.executePostRequest(
                (UPLOAD_FILE_URL
                        + QUERY_PATH_SEPARATOR
                        + FOLDER_ID_PARAMETER
                        + PICTURE_FOLDER_ID
                        + QUERY_PARAMETER_SEPARATOR
                        + FILE_NAME_PARAMETER
                        + file.getOriginalFilename()
                        + QUERY_PARAMETER_SEPARATOR
                        + AUTH_PARAMETER
                        + this.cloudAuthorizationService.getAccessToken())
                , file.getContentType()
                , file.getBytes()
        ).body().string();

        Map<String, Object> imageDataJSON = gson.fromJson(uploadImageResponse, Map.class);

        ArrayList<Object> imageMetadataInArray =(ArrayList<Object>) imageDataJSON.get("metadata");

        Map<String, Object> imageMetadata = (Map<String, Object>)imageMetadataInArray.get(0);

        String imageId = (String) imageMetadata.get("id").toString().substring(1);

        String fileListJsonResult = this.httpRequestExecutor.executeGetRequest(
                LIST_FILE_URL
                        + QUERY_PATH_SEPARATOR
                        + FILE_ID_PARAMETER
                        + imageId
                        + QUERY_PARAMETER_SEPARATOR
                        + AUTH_PARAMETER
                        + accessToken
        ).body()
                .string();


        String fileCode = gson.fromJson(fileListJsonResult, Map.class).get("code").toString();

        String fileDownloadJsonResult = this.httpRequestExecutor.executeGetRequest(
                DOWNLOAD_FILE_URL
                        + QUERY_PATH_SEPARATOR
                        + CODE_PARAMETER
                        + fileCode
        ).body()
                .string();

        Map<String, Object> fileDownloadData = gson.fromJson(fileDownloadJsonResult, Map.class);

        String filePath = fileDownloadData.get("path").toString();
        String host = ((ArrayList<String>) fileDownloadData.get("hosts")).get(0);

        String fileName = imageMetadata.get("name").toString();
        String fileUrl = "https://" + host + filePath;

        return fileUrl;
    }
}
