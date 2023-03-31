import { API_URL, POST_METHOD } from './constants.js';
import { bhaskaraValidation, pitagorasValidation } from './validators.js';

$(document).ready(function() {
    $("#navbar").load("components/navbar.html");

    $('.form-3-fields').submit(function(e) {
        $('#div-resolution').html('');
        $('#div-response').html('');

        e.preventDefault();

        let form = $(this);
      
        var formData = form.serializeArray().map((it) => it['value']);

        const request = {
            a: formData[0],
            b: formData[1],
            c: formData[2]
        };

        let formSecondaryClass = form.attr('class').split(' ')[1];

        switch(formSecondaryClass) {
            case 'bhaskara':
                bhaskaraValidation(request);
                break;
            default:
                pitagorasValidation(request);
        };
        
        sendRequest(POST_METHOD, `${API_URL}/${formSecondaryClass}`, JSON.stringify(request), (data) => {
            let resolution = data['resolution'];
            let response = data['response'];

            resolution.forEach(element => {
                $('#div-resolution').append(`${element}<br>`);
            });

            $('#div-response').append(`Resultado final: ${response}`);

        });
    });
});

function sendRequest(method, url, data, success) {
    $.ajax({
        method: method,
        url: url,
        data: data,
        contentType: "application/json",
        success: function(response) {
            if (typeof success === 'function') {
                success(response);
            }
        },
    });
}
