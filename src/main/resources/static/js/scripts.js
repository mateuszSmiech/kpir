//datepicker
$(
    function() {

    $( "#exampleConfirmPassword" ).datepicker({
        dateFormat: "yy-mm-dd"
    });

});

//load current month
var get_url= window.location.href;
var url = new URL(get_url);
var paramValue = url.searchParams.get("month");

function SelectElement(id, valueToSelect)
{
    var element = document.getElementById(id);
    element.value = valueToSelect;
}

SelectElement("month", paramValue)








