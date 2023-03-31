export function bhaskaraValidation(request) {
    if (isNaN(request['a']) || request['a'] == '') {
        $('#div-response').html('DEU ERRO');
        throw Error('Valor inválido para A');
    }

    if (isNaN(request['b']) && request['b'] != '') {
        $('#div-response').html('DEU ERRO');
        throw Error('Valor inválido para B');
    }

    if (isNaN(request['c']) && request['c'] != '') {
        $('#div-response').html('DEU ERRO');
        throw Error('Valor inválido para C');
    }
}

export function pitagorasValidation(request) {
    let emptyFields = 0;

    if (isNaN(request['a']) || isNaN(request['b']) || isNaN(request['c'])) {
        throw Error('Valor inválido para pelo menos um dos campos')
    }

    if (request['a'] == '') {
        emptyFields++;
    }

    if (request['b'] != '') {
        emptyFields++;
    }

    if (request['c'] != '') {
        emptyFields++;
    }

    if (emptyFields > 1) {
        $('#div-response').html('Você deve preencher 2 dos 3 campos disponíveis para realizar a operação!')
        throw Error('O usuário deve preencher 2 dos 3 campos disponíveis para realizar a operação!')
    }
}