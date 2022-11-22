function searchUser(query){
    $.ajax({
        url: "/admin/searchUser",
        type: "post",
        data:{key:query},
        success: function (){
            let userHtml = '<ul class="list-group list-group-flush">' +
                '<li class="list-group-item" th:text="${UserID}"></li>' +
                '<li class="list-group-item" th:text="${Nickname}"></li>' +
                '<li class="list-group-item" th:text="${Email}"></li>' +
                '</ul>'
            $("#userDisplay").html(userHtml)
        }
    })
}

function renderDetail(title){
    $.ajax({
        url: "/bookDetail",
        type: "post",
        async:false,
        data:{title:title},
        success: function (detail) {
            $("#bookConsole").html('<form action="/admin/editBook">'+
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
        '<button type="submit" class="btn btn primary btn-lg">Submit</button></form>')
}

function searchBook(query){
    $.ajax({
        type: "POST",
        url: "/searchBook",
        async: false,
        data:{query:query},
        success:function (list) {
            let listHtml = ''
            $.each(list, function (i, value) {
                listHtml +='<tr class="table-primary" onClick="renderDetail(\'' + value.title + '\')">' +
                    '<th scope="row">' + value.title + '</th><td>' + value.author + '</td><td>' + value.isbn + '</td></tr>'
            })
            $("#bookListDisplay").html(listHtml)
        }
    })
}