Request = {
    QueryString : function(item){
        var svalue = location.search.match(new RegExp("[\?\&]" + item + "=([^\&]*)(\&?)","i"));
        return svalue ? svalue[1] : svalue;
    }
};

$(document).ready(
    function () {
        const bookTitle = decodeURIComponent(Request.QueryString("title"));
        $.ajax({
            url: "/bookDetail",
            data:{title:bookTitle},
            type:"get",
            async:false,
            success:function (bookInfo){
                console.log(bookInfo)
                $("#bookBody").html('<div class="card-header">'+bookInfo.title+'</div>' +
                '<div class="card-body">' +
                '  <p class="card-title">'+bookInfo.author+'</p>' +
                '  <p class="card-text">description</p>' +
                '</div>')

                $("#availCount").text(bookInfo.avail);
                $("#pendingCount").text(bookInfo.pending);
                $("#borrowedCount").text(bookInfo.processing);
            }
        })
    }
)