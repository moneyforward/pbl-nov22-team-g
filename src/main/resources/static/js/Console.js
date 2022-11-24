$(document).ready(function () {
    $.ajax({
        url:"/findOverdueBook",
        async:false,
        success: function (list){
            let recordHtml = ''
            $.each(list, function (i, record){
                recordHtml += '<tr class="table-primary">' +
                    '<th scope="row">'+record.isbn+'</th><td>'+record.title+'</td><td>'+record.userID+'</td>' +
                    '<td>'+record.startDate+'</td><td>'+record.endDate+'</td></tr>'
            })
            $("#overdueBody").html(recordHtml)
        }
    })
})

function banUser(uid){
    $.ajax({
        url:"/banUser",
        type: "post",
        async: false,
        data: {userCode: uid},
        success: function () {
            alert("Success")
            location.href="/console"
        }
    })
}

function unBanUser(uid){
    $.ajax({
        url:"/unBanUser",
        type: "post",
        async: false,
        data: {userCode: uid},
        success: function () {
            alert("Success")
            location.href="/console"
        }
    })
}


function renderDetail(bid){
    location.href="/console#t_3"
    $.ajax({
        url: "/admin/findbookbyID",
        type: "post",
        async:false,
        data:{BookID:bid},
        success: function (detail) {
            $("#bookConsole").html('<form action="/admin/editBook">'+
                '<div class="form-group">' +
                '<label class="col-form-label mt-4" for="inputDefault">BookID</label>' +
                '<input type="text" name="BookID" class="form-control" placeholder="Enter the ID" id="inputDefault" value="'+bid+'"></div>' +
                '<div class="form-group">' +
                '<label class="col-form-label mt-4" for="inputDefault">Title</label>' +
                '<input type="text" name="title" class="form-control" placeholder="Enter the title" id="inputDefault" value="'+detail.title+'"></div>' +
                '<div class="form-group">' +
                '<label class="col-form-label mt-4" for="inputDefault1">Author</label>' +
                '<input type="text" name="author" class="form-control" placeholder="Enter the author" id="inputDefault1" value="'+detail.author+'"></div>'+
                '<div class="form-group">' +
                '<label class="col-form-label mt-4" for="inputDefault2">ISBN</label>' +
                '<input type="text" name="ISBN" class="form-control" placeholder="Enter the ISBN" id="inputDefault2" value="'+detail.isbn+'"></div>'+
                '<button type="submit" class="btn btn-primary btn-lg">Submit</button></form>')
        }
    })
}
function renderBlank(){
    $("#bookConsole").html('<form action="/admin/addbook">'+
        '<div class="form-group">' +
        '<label class="col-form-label mt-4" for="inputDefault">Title</label>' +
        '<input type="text" name="title" class="form-control" placeholder="Enter the title" id="inputDefault"></div>' +
        '<div class="form-group">' +
            '<label class="col-form-label mt-4" for="inputDefault1">Author</label>' +
            '<input type="text" name="author" class="form-control" placeholder="Enter the author" id="inputDefault1"></div>'+
        '<div class="form-group">' +
        '<label class="col-form-label mt-4" for="inputDefault2">ISBN</label>' +
        '<input type="text" name="ISBN" class="form-control" placeholder="Enter the ISBN" id="inputDefault2"></div>'+
        '<button type="submit" class="btn btn-primary btn-lg">Submit</button></form>')
}

function searchBook(query){
    $.ajax({
        type: "POST",
        url: "/admin/searchsinglebook",
        async: false,
        data:{query:query},
        success:function (list) {
            let listHtml = ''
            $.each(list, function (i, value) {
                listHtml +='<tr class="table-primary" onclick="renderDetail(\'' + value.bookID + '\')">' +
                    '<th scope="row">' + value.title + '</th><td>' + value.author + '</td><td>' + value.isbn + '</td></tr>'
            })
            $("#bookListDisplay").html(listHtml)
        }
    })
}