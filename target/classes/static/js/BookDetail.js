Request = {
    QueryString : function(item){
        const svalue = location.search.match(new RegExp("[\?\&]" + item + "=([^\&]*)(\&?)","i"));
        return svalue ? svalue[1] : svalue;
    }
};
const bookTitle = decodeURIComponent(Request.QueryString("title"));
$("title").text(bookTitle);

$(document).ready(
    function () {
        $.ajax({
            url: "/bookDetail",
            data:{title:bookTitle},
            async:false,
            success:function (bookInfo){
                $("#bookBody").html('<div class="card-header">'+bookInfo.title+'</div>' +
                '<div class="card-body">' +
                '  <p class="card-title">'+bookInfo.author+'</p>' +
                '  <p class="card-text">description</p>' +
                '</div>')

                $("#availCount").text(bookInfo.avail);
                if(bookInfo.avail==="0"){
                    $("#reserve").attr("class", "btn btn-secondary book-detail disabled")
                }
                $("#pendingCount").text(bookInfo.pending);
                $("#borrowedCount").text(bookInfo.processing);
            }
        })
    }
)

function addReadPlan(){
    if(!ifLogin("user")){
        location.href="/login"
    }
    $.ajax({
        url:"/addReadPlan",
        data:{title: bookTitle},
        async:true,
        success:function (e){
            alert("'"+bookTitle + "' has been added.")
        }
    })
}
function reserveBook(e){
    const titleVar = e===''? bookTitle:e;
    if(!ifLogin("user")){
        location.href="/login"
    }
    $.ajax({
        url:"/reserveBook",
        data:{title: titleVar},
        async:true,
        success:function (flag){
            if(flag) {
                alert("'" + titleVar + "' has been added. Please go library and ask check out in 24H")
            }else{
                alert("'" + titleVar + "' is out of stock")
            }
        }
    })
}
