Html5Qrcode.getCameras().then(devices => {
    if(devices && devices.length){
        const cameraId = devices[0].id;
        const qrcode = new Html5Qrcode("reader")
        qrcode.start(cameraId, {fps:10, qrbox:260},
            (decodedText, decodedResult) => {
            if(decodedText.startsWith("u")){
                readUserQRCode(decodedText)
                location.href="#t_5"
            }
            if(decodedText.startsWith("b")){
                checkOut(decodedText)
            }
        }
        ).catch((err)=>{
            console.log(err)
        })
    }
}).catch(err => {
    console.log(err)
})

function readUserQRCode(userCode){
    $.ajax({
        url:"/readUserQRCode",
        type:"post",
        async:false,
        data:{userCode:userCode},
        success: function (records){
            let recordsHtml = '<table class="table table-hover"><thead><tr><th scope="col">Title</th><th scope="col">Author</th><th scope="col">StartDate</th><th scope="col">Deadline</th><th scope="col">Status</th>' +
                '</tr></thead><tbody id="inProgressBody">'
            $.each(records, function (i, record){
                recordsHtml += '<tr class="table-primary">' +
                    '<th scope="row">'+record.title+'</th><td>'+record.author+'</td>' +
                    '<td>'+record.startStr+'</td><td>'+record.endStr+'</td><td>'+record.status+'</td></tr>'
            })
            recordsHtml += '</tbody></table><button class="btn btn-success" onclick="checkIn(\''+userCode+'\')">Check In</button>'
            $("#t_5").html(recordsHtml)
        }
    })
}

function checkIn(userCode){
    $.ajax({
        url:"/checkInBook",
        type:"post",
        async:false,
        data:{userCode:userCode},
        success: function (e){
            alert(e)
            location.href="/console"
        }
    })
}

function checkOut(bookCode){
    const bookid = (bookCode.substring(1)-414)/42
    $.ajax({
        url:"/returnBook",
        data:{bookID:bookid},
        async:false,
        type:"post",
        success:function (e){
            alert("Success!")
            location.href=""
        }
    })
}