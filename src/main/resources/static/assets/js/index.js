import {API_URL, POST_METHOD} from './constants.js';
import {bhaskaraValidation, interestValidation, pitagorasValidation} from './validators.js';

$(document).ready(function () {
    $("#navbar").load("components/navbar.html");

    $('.interest-form').submit(function (e) {
        $('#div-resolution').html('');
        $('#div-response').html('');

        e.preventDefault();

        let form = $(this);

        const formData = form.serializeArray().map((it) => it['value']);

        let isSimpleInterest = formData[5];
        let isRateTimeUnitInMonths = $('input[name=rateTimeUnit]:checked', '.interest-form').val() === 'month';
        let isDurationUnitInMonths = $('input[name=durationTimeUnit]:checked', '.interest-form').val() === 'month';

        const request = {
            interest: formData[0],
            amount: formData[1],
            rate: (isRateTimeUnitInMonths ? formData[2] : formData[2] / 12) / 100,
            capital: formData[3],
            duration: isDurationUnitInMonths ? formData[4] : formData[4] * 12,
        };

        interestValidation(request);

        let interestType = isSimpleInterest ? "simple" : "compound";

        sendRequest(POST_METHOD, `${API_URL}/interest/${interestType}`, JSON.stringify(request), (data) => {
            let resolution = data['resolution'];
            let response = data['result'];

            resolution.forEach(element => {
                $('#div-resolution').append(`${element}<br>`);
            });

            console.log(response);

            $('#div-response').append(`Resultado final: <br>${response}`);
        })
    });

    $('.form-3-fields').submit(function (e) {
        $('#div-resolution').html('');
        $('#div-response').html('');

        e.preventDefault();

        let form = $(this);

        const formData = form.serializeArray().map((it) => it['value']);

        const request = {
            a: formData[0],
            b: formData[1],
            c: formData[2]
        };

        let formSecondaryClass = form.attr('class').split(' ')[1];

        switch (formSecondaryClass) {
            case 'bhaskara':
                bhaskaraValidation(request);
                break;
            default:
                pitagorasValidation(request);
        }

        sendRequest(POST_METHOD, `${API_URL}/${formSecondaryClass}`, JSON.stringify(request), (data) => {
            let resolution = data['resolution'];
            let response = data['result'];

            resolution.forEach(element => {
                $('#div-resolution').append(`${element}<br>`);
            });

            console.log(response);

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
        success: function (response) {
            if (typeof success === 'function') {
                success(response);
            }
        },
    });
}
