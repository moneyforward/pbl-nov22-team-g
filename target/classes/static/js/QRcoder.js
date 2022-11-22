// user qr code
$(document).ready(function() {
    $.ajax({
        url: '/getUserId',
        method:'POST',
        async: false,
        success: function (useraid){
            $("#qrcodeCanvas").qrcode({
                render : "canvas",
                text : useraid,
                background : "#ffffff",
                foreground : "#000000",
                src: '../saltedfish.png'
            });
        }
    })
});