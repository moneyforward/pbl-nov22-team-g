/*
pkg: https://github.com/js-cookie/js-cookie
 */
// import Cookies from "/js/libs/js.cookie.mjs"

/* cookie required list
username;
session required:
adminid; userid; Overdue(user overdue status)

 */

// use jquery coookie
$(document).ready(function (){
    if($.cookie("username")!=null){
        $("#login-btn").hide()
        $("#username").text($.cookie("username"))
    }else{
        $("#logout").hide()
        $("#username").hide()
    }
    if($.cookie("status")!=="-1") {
        if($.cookie("status")==="0"){
            $("#overdueAlert").text("Borrowed book is over 10")
        }
        if($.cookie("status")==="1"){
            $("#overdueAlert").text("Book Overdue")
        }
        if($.cookie("status")==="2"){
            $("#overdueAlert").text("Baned by librarians")
        }
    }else{
        $("#overdueAlert").hide()
    }
})

function logOut(){
    $.removeCookie("username")
    $.ajax({
        url: "/logout"
    })
    location.href=""
}

function ifLogin(type){
    if(type==="user") {
        let loginStatus = false;
        $.ajax({
            url:"/checkLogin",
            method:"post",
            async:false,
            success: function (e){
                loginStatus = e
            }
        })
        return loginStatus
    }else{
        return $.cookie("Email")!=null
    }
}