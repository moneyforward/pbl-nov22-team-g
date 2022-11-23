Html5Qrcode.getCameras().then(devices => {
    if(devices && devices.length){
        const cameraId = devices[0].id;
        const qrcode = new Html5Qrcode("reader")
        qrcode.start(cameraId, {fps:10, qrbox:260},
            (decodedText, decodedResult) => {
            if(decodedText.startsWith("u")){
                location.href="/console#t_5"
                readUserQRCode(decodedText.substring(1))
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
        async:true,
        data:{userCode:userCode},
        success: function (records){
            let recordsHtml = '<table class="table table-hover"><thead><tr><th scope="col">Title</th><th scope="col">Author</th><th scope="col">StartDate</th><th scope="col">Deadline</th><th scope="col">Status</th>' +
                '</tr></thead><tbody id="inProgressBody">'
            $.each(records, function (i, record){
                recordsHtml += '<tr class="table-primary">' +
                    '<th scope="row">'+record.title+'</th><td>'+record.author+'</td>' +
                    '<td>'+record.startStr+'</td><td>'+record.endStr+'</td><td>'+record.status+'</td></tr>'
            })
            recordsHtml += '</tbody></table><button class="btn btn-success" onclick="checkIn('+userCode+')">Check In</button>'
            $("#t_5").html(recordsHtml)
        }
    })
}

function checkIn(userid){
    $.ajax({
        url:"checkin",
        data:{userCode:userid},
        success: function (e){
            if(!e){
                alert("Invalid User")
            }else{
                location.href="/console#t_5"
            }
        }
    })
}

function checkOut(bookCode){
    const bookid = (bookCode.substring(1)-414)/42
    $.ajax({
        url:"/returnbook",
        data:{bookid:bookid},
        success:function (e){
            alert("Success!")
        }
    })
}