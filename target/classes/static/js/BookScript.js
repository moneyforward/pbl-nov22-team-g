let pages = []
let pageCur = 0

$(document).ready(
    function (){
        getBookList()
        renderPage(pageCur, 10)
        renderPageBar(pageCur, 10)
    }
)

function getBookList(){
    $.ajax({
        type:"POST",
        url:"/bookList",
        async:false,
        success:function(list) {
            let buffer = ""
            $.each(list, function (i, value) {
                pages.push('<tr class="table-primary" onClick="tableInnerLink(\''+value.title+'\')">' +
                    '<th scope="row">'+value.title+'</th><td>'+value.author+'</td><td>'+value.stock+'</td></tr>')
            })
        }
    })
}

function renderPage(pageIndex, pageSize){
    const cur = pageIndex*pageSize
    let listHtml = ""
    for(let i=cur;i<cur+pageSize&&i<pages.length;++i){
        listHtml += pages[i]
    }
    $("#bookListBody").html(listHtml);
}

function renderPageBar(pageIndex, pageSize){
    const maxPage = Math.ceil(pages.length/pageSize);
    let barHtml = '<ul class="pagination">'
    if(pageIndex!==0){
        barHtml += '<li class="page-item"><a class="page-link" ' +
            'onclick="renderPage('+(pageIndex-1)+','+pageSize+');renderPageBar('+(pageIndex-1)+','+pageSize+')">&laquo;</a></li>'
    }
    const start = pageIndex-5 > 0 ? pageIndex-5:0
    const end = pageIndex+5 <= maxPage ? pageIndex+5:maxPage

    for(let i=start; i<end; ++i){
        if(i === pageIndex){
            barHtml += '<li class="page-item active"><a class="page-link" ' +
                'onclick="renderPage('+i+','+pageSize+');renderPageBar('+i+','+pageSize+')">'+(i+1)+'</a></li>'
        }else{
            barHtml += '<li class="page-item"><a class="page-link" ' +
                'onclick="renderPage('+i+','+pageSize+');renderPageBar('+i+','+pageSize+')"">'+(i+1)+'</a></li>'
        }
    }
    if(pageIndex!==maxPage){
        barHtml += '<li class="page-item"><a class="page-link" ' +
            'onclick="renderPage('+(pageIndex+1)+','+pageSize+');renderPageBar('+(pageIndex+1)+','+pageSize+')">&raquo;</a></li>'
    }
    barHtml += '</ul>'
    $("#pagebar").html(barHtml)
}

function changeShownRow(num){
    console.log(num)
    renderPage(0, num)
    renderPageBar(0, num)
}