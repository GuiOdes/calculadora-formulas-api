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

export function interestValidation(request) {
    let interest = request["interest"] ?? '';
    let capital = request["capital"] ?? '';
    let amount = request["amount"] ?? '';
    let rate = request["rate"] ?? '';
    let duration = request["duration"] ?? '';
    let isSimpleInterest = request["is-simple"] ?? '';

    if (isNaN(interest) || isNaN(amount) || isNaN(rate) || isNaN(capital) || isNaN(duration)) {
        throw Error('Valor inválido para pelo menos um dos campos');
    }

    let message = "Impossível calcular: ";

    if (interest == null && capital == null && isSimpleInterest) {
        message += 'Você deve preencher o campo de juros ou de capital.';
    } else if (capital == null && amount == null && !isSimpleInterest) {
        message += 'Capital e Montante não podem ser nulos ao mesmo tempo no cálculo de juros compostos.';
    } else if (rate == null && interest == null && amount == null && isSimpleInterest) {
        message += 'A taxa não pode ser nula juntamente com juros e montante.';
    } else if (rate == null && capital == null && amount == null) {
        message += 'A taxa não pode ser nula juntamente com capital e montante.';
    } else if (duration == null && interest == null && amount == null && isSimpleInterest) {
        message += 'A duração não pode ser nula juntamente com juros e montante em um cálculo de juros simples.';
    } else if (rate == null && duration == null) {
        message += 'A taxa não pode ser nula juntamente com a duração.';
    }

    if (message.length > 21) {
        throw Error(message);
    }
}