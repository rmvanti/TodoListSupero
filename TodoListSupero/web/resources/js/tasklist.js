$(document).ready(function (event) {

    var baseURL = 'http://localhost:8084/TodoListSupero/rs/task';
    var getList = baseURL;
    var btnOpenModal = $('.btnOpenModal');
    var modal = $('#taskCrudModal');
    var tbody = $('table').find('tBody');

    function clearModalFields() {
        modal.find('.doneField').attr('checked', false);
        modal.find('.titleField').val('');
        modal.find('.descriptionField').val('');
    }

    function loadTable() {

    }

    btnOpenModal.click(function (ev) {
        ev.preventDefault();
        $this = $(this);
        clearModalFields();
        modal.find('.btnModalSave').show();
        modal.find('.btnModalUpdate').hide();
        modal.modal('show');
    });
    function getGridData() {
        var invocation = new XMLHttpRequest();
        invocation.open('GET', getList, true);
        invocation.withCredentials = false;
        invocation.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        invocation.onreadystatechange = function () {
            if (invocation.readyState === 4 && invocation.status === 200) {
                var json = invocation.response;
                var line = '';
                json = typeof json === 'string' ? JSON.parse(json) : json;
                $('table tbody tr').remove();
                for (var i = 0; i < json.length; i++) {
                    line = '<tr data-id="' + json[i].id + '">';
                    line += '<td><input type="hidden" value="' + json[i].id + '"></input></td>';
                    line += '<td> <input type="checkbox" class="rowCheck" data-id="' + json[i].id + '" checked="' + json[i].done + '"/> </td>';
                    line += '<td><span>' + json[i].title + '</span></td>';
                    line += '<td><span>' + json[i].description + '</span></td>';
                    line += '<td><span>' + json[i].createdIn + '</span></td>';
                    line += '<td><span>' + json[i].updatedIn + '</span></td>';
                    line += '<td> <form>';
                    line += '<button class="btn btn-warning btn-xs btnEdit" data-id="' + json[i].id + '">Editar</button>';
                    line += '<button class="btn btn-danger btn-xs btnDelete" data-id="' + json[i].id + '">Remover</button>';
                    line += '</form></td>';
                    line += '</tr>';
                    $('table tbody').append(line);
                }
            }
        },
                invocation.send();
    }



    function formToJson() {
        return JSON.stringify({
            'id': modal.find('.taskIdField').val(),
            'done': modal.find('.doneField').attr('checked'),
            'title': modal.find('.titleField').val(),
            'description': modal.find('.descriptionField').val(),
            'doneDate': modal.find('.doneDateField').val(),
            'createdIn': modal.find('.createdInField').val(),
            'updatedIn': modal.find('.updatedInField').val()
        });
    }

    function jsonToForm(json) {
        modal.find('.taskIdField').val(json.id);
        modal.find('.doneField').val(json.done);
        modal.find('.titleField').val(json.title);
        modal.find('.descriptionField').val(json.description);
    }

    tbody.on('click', 'btnEdit', function (ev) {
        ev.preventDefault();
        modal.find('.btnModalSave').hide();
        modal.find('.btnModalUpdate').show();
        modal.modal('show');
    });

    tbody.on('click', 'btnDelete', function (ev) {
        ev.preventDefault();
        $this = $(this);
        var invocation = new XMLHttpRequest();
        var url = baseURL + "/" + $this.attr('data-id');
        invocation.open('DELETE', url, true);
        invocation.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        invocation.withCredentials = false;
        invocation.onreadystatechange = function () {
            if (invocation.readyState === 4 && invocation.status === 200) {
                var json = invocation.response;
                json = typeof json === 'string' ? JSON.parse(json) : json;
                getGridData();
            }
        };
        invocation.send();
    });


    modal.on("click", ".btnModalSave", function (ev) {
        ev.preventDefault();
        var invocation = new XMLHttpRequest();
        invocation.open('POST', baseURL, true);
        invocation.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        invocation.withCredentials = false;
        invocation.onreadystatechange = function () {
            if (invocation.readyState === 4 && invocation.status === 200) {
                getGridData();
                modal.modal('hide');
            }
        };
        var dataToSend = formToJson();
        invocation.send(dataToSend);
    });

    /*
     $this = $(this);
     var invocation = new XMLHttpRequest();
     invocation.open('POST', confirmPath, true);
     invocation.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
     invocation.withCredentials = false;
     invocation.onreadystatechange = function () {
     if (invocation.readyState === 4 && invocation.status === 200) {
     var json = invocation.response;
     json = typeof json === 'string' ? JSON.parse(json) : json;
     refreshConfirm(json);
     }
     };
     var dataToSend = JSON.stringify({
     'password': userInfoForm.find('.passwordField').val(),
     'confirmPassword': userInfoForm.find('.confirmPasswordField').val(),
     'passwordOk': userInfoForm.find('.passwordOkField').val()
     });
     invocation.send(dataToSend);
     */

    getGridData();
});


