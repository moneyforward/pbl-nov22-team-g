
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

function tableInnerLine(bookTitle){
    location.href="/bookdetail?"+bookTitle
}