package com.lmao.areas.images.respositories;

import com.lmao.areas.images.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ImageRepository extends JpaRepository<Image, String> {


    @Query(value = "SELECT * FROM images AS i ORDER BY i.uploaded_date DESC LIMIT 3", nativeQuery = true)
    List<Image> getInitialImagesForHome();

    @Query(value = "SELECT * FROM images AS i WHERE i.category_id = :categoryId ORDER BY i.uploaded_date DESC LIMIT 3", nativeQuery = true)
    List<Image> getInitialImagesForHomeWithCategory(@Param("categoryId") String categoryId);

    @Query(value = "SELECT * FROM images AS i ORDER BY i.uploaded_date DESC LIMIT :load OFFSET :loadedImagesCount", nativeQuery = true)
    List<Image> getImagesForHomePageWithOffset(
            @Param("loadedImagesCount") int loadedImagesCount,
            @Param("load") int load);

    @Query(value = "SELECT * FROM images AS i WHERE i.category_id = :categoryId ORDER BY i.uploaded_date DESC LIMIT :load OFFSET :loadedImagesCount", nativeQuery = true)
    List<Image> getImagesForHomePageWithOffsetAndCategory(
            @Param("loadedImagesCount") int loadedImagesCount,
            @Param("load") int load,
            @Param("categoryId") String categoryId);

    @Query(value = "SELECT * FROM images AS i WHERE i.id = :id", nativeQuery = true)
    Image findOne(@Param("id") String id);

}
