
$(document).ready(function () {
    $('.table .btn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (user, status) {
            $('#idEdit').val(user.id);
            $('#firstNameEdit').val(user.firstName);
            $('#lastNameEdit').val(user.lastName);
            $('#ageEdit').val(user.age);
            $('#emailEdit').val(user.email);
            $('#passwordEdit').val(user.password);
            // $('#rolesEdit').val(user.role);
        });
        $('#editModal').modal();
    });
});