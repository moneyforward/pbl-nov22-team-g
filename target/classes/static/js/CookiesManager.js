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
    if($.cookie("Overdue")!=null) {
        const alert = $("#overdueAlert")
        alert.show()
        if ($.cookie("Overdue") === "1"){
            alert.html("You need to return the book, check the reservation page")
        }else {
            alert.html("You will be unblocked by "+$.cookie("Overdue"))
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
        let loginStatus;
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
        return $.session.get("adminid") != null
    }
}