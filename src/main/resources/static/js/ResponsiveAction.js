
function showSideList(){
    $("#navbarColor02").animate({height:'toggle'}, 350)
}

function showDropdown(menuId){
    const elementId = "#dropdown" + menuId;
    $(elementId).animate({height:'toggle'}, 300)
}

function editEmail(){
    $("#emailDisabledInput").removeAttr("disabled")
}

function tableInnerLink(bookTitle){
    location.href="/bookdetail?title="+bookTitle
}

function checkPassValid(){
    const oriPass = $("#exampleInputPassword1")
    const repeatPass = $("#exampleInputPassword2")
    const submitBtn = $("#signUpSubmit")

    const regExp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;

    let flag1 = false;
    let flag2 = false;
    if(regExp.test(oriPass.val())){
        oriPass.attr("class", "form-control is-valid")
        flag1 = true
    }else{
        oriPass.attr("class", "form-control is-invalid")
        submitBtn.attr("disabled","")
    }

    if(oriPass.val() === repeatPass.val()){
        repeatPass.attr("class", "form-control is-valid")
        flag2 = true
    }else{
        repeatPass.attr("class", "form-control is-invalid")
        submitBtn.attr("disabled","")
    }

    console.log(flag1, flag2)
    if(-(flag1)-(flag2)===-2){
        submitBtn.removeAttr("disabled", "")
    }
}