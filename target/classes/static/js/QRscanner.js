
Html5Qrcode.getCameras().then(devices => {
    if(devices && devices.length){
        var cameraId = devices[0].id;
        const qrcode = new Html5Qrcode("reader")
        qrcode.start(cameraId, {fps:10, qrbox:260},
            (decodedText, decodedResult) => {console.log(decodedText)}
        ).catch((err)=>{
            console.log(err)
        })
    }
}).catch(err => {
    console.log(err)
})