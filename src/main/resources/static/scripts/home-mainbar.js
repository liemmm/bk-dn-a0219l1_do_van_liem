let counter;
let selectedCategory;
$(document).ready(function () {
    selectedCategory = "all";
    counter = 0;
    loadJSONInitialImageLaughs();
    window.onscroll = dynamiclyImageLoader;
});

function changeSelectedCategory(element, categoryId) {
    selectedCategory = categoryId;
    $('#selectedCategory').remove();
    $(element).append('<span id="selectedCategory" class="badge badge-default badge-pill"><i class="fa fa-check"></i>')
    $('#mainBar').empty();
    counter = 0;
    loadJSONInitialImageLaughs();
    window.onscroll = dynamiclyImageLoader;
}

function loadJSONInitialImageLaughs() {
    $.ajax({
        type: 'GET',
        url: '/image/get-initial-images-for-mainbar?categoryId=' + selectedCategory,
        data: 'json',
        success: function (images) {
            loadDOMImagesLaughs(images);
        }
    });
}

function loadDOMImagesLaughs(laughs) {
    counter += laughs.length;
    $.each(laughs, function (i, laugh) {
            var id = laugh.id;
            var description = laugh.description;
            var imageUrl = laugh.imageUrl;
            var uploadedDate = laugh.uploadedDate;
            var uploader = laugh.user;
            var likes = laugh.likes;
            var dislikes = laugh.dislikes;

            $('#mainBar')
                .append($('<a></a>')
                    .addClass('image-tag')
                    .attr("href", "/image/" + id)
                    .append($('<h2></h2>')
                        .text(description))
                    .append($('<br/>'))
                    .append($('<img>')
                        .addClass('card-image')
                        .attr("src", imageUrl)
                        .attr("id", id))
                    .append($('<br/>'))
                    .append($('<hr/>')))
        })
}

function dynamiclyImageLoader() {
    var wrap = document.getElementById('mainBar');
    var contentHeight = wrap.offsetHeight;
    var yOffset = window.pageYOffset;
    var y = yOffset + window.innerHeight;
    if (y >= contentHeight) {
        loadJSONDynamiclyImageLaughs();
        window.onscroll = null;
    }
}

function loadJSONDynamiclyImageLaughs() {
    $.ajax({
        type: 'GET',
        url: '/image/get-dynamically-loaded-images-for-mainbar?loadedImagesCount=' + counter + '&categoryId=' + selectedCategory,
        data: 'json',
        success: function (laughs) {
            loadDOMImagesLaughs(laughs);
            window.onscroll = dynamiclyImageLoader;
        }
    });
}