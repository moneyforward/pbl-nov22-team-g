// user qr code
$(document).ready(function() {
    $("#qrcodeCanvas").qrcode({
        render : "canvas",    //设置渲染方式，有table和canvas，使用canvas方式渲染性能相对来说比较好
        text : "18274231",    //扫描二维码后显示的内容,可以直接填一个网址，扫描二维码后自动跳向该链接
        background : "#ffffff",       //二维码的后景色
        foreground : "#000000",        //二维码的前景色
        src: '/saltedfish.png'             //二维码中间的图片
    });
});