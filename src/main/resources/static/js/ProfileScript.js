$(document).ready(
    function () {
        getReadPlans()
        getInProgress()
        getHistory()
    }
)

function getReadPlans(){
    $.ajax({
        url:"/getReadPlans",
        type:"post",
        async:false,
        success: function (plans){
            let planHtml = ''
            $.each(plans, function (i, plan){
                let btnClass = "btn btn-info btn-sm"
                if(plan.statusCount === 0){
                    btnClass += " disabled"
                }
                planHtml += '<tr class="table-primary">' +
                    '<th scope="row" onclick="tableInnerLink('+plan.title+')">'+plan.title+'</th><td>'+plan.statusCount+'</td>' +
                    '<td><button class="'+btnClass+'" onclick="reserveBook('+plan.title+')">Reserve</button></td>' +
                    '<td><button class="btn btn-danger btn-sm" onclick="deletePlan('+plan.title+')">Delete</button></td></tr>'
            })
            $("#planBody").html(planHtml)
        }
    })
}

function deletePlan(title){
    $.ajax({
        url:"/deletePlans",
        type:"post",
        async: false,
        data:{title:title},
        success: function (e){
            location.href = "/profile#tab_2"
        }
    })
}

function getInProgress(){
    $.ajax({
        url:"/getInProgress",
        type:"post",
        async: false,
        success: function (list){
            let recordHtml = ''
            $.each(list, function (i, record){
                recordHtml += '<tr class="table-primary">' +
                '<th scope="row">'+record.title+'</th><td>'+record.author+'</td>' +
                '<td>'+record.startStr+'</td><td>'+record.endStr+'</td><td>'+record.status+'</td></tr>'
            })
            $("#inProgressBody").html(recordHtml)
        }
    })
}

function getHistory(){
    $.ajax({
        url:"/getHistory",
        type:"post",
        async: false,
        success: function (list){
            let recordHtml = ''
            $.each(list, function (i, record){
                recordHtml += '<tr class="table-primary">' +
                    '<th scope="row">'+record.title+'</th><td>'+record.author+'</td>' +
                    '<td>'+record.startStr+'</td><td>'+record.endStr+'</td></tr>'
            })
            $("#historyBody").html(recordHtml)
        }
    })
}
