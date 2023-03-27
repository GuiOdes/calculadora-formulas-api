$(document).ready(() => {
    $("#navbar").load("components/navbar.html");
});

$("#formBhaskara").submit((e) => {
    e.preventDefault();

    sendRequest("GET", "https://viacep.com.br/ws/01001000/json/", null, (data) => {
        alert(data["cep"]);
    })
});

function sendRequest(method, url, data, success) {
    $.ajax({
        method: method,
        url: url,
        data: data,
        success: function(response) {
            if (typeof success === 'function') {
                success(response);
            }
        },
    });
}