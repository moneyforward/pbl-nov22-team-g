function getBookList(){
    $.ajax({
        type:"POST",
        url:"/controllerMapping",
        success:function(list){
            let listHtml = "<>render";
            // need bookTitle; bookAuthor; bookStock
            $.each(list, function (i, value){
                listHtml += value.bookTitle
            })
        }
    })
}