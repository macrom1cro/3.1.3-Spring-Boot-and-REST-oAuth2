// alert('hi');
fetch("http://localhost:8080/admin/users").then(
    res => {
        res.json().then(
            data => {
                console.log(data);
                if (data.length > 0) {
                    var temp = "";
                    var generatedHTML = "";
                    data.forEach((u) => {
                        temp += "<tr>"
                        temp += "<td>" + u.id + "</td>"
                        temp += "<td>" + u.firstName + "</td>"
                        temp += "<td>" + u.lastName + "</td>"
                        temp += "<td>" + u.age + "</td>"
                        temp += "<td>" + u.email + "</td>"
                        temp += "<td>" + u.roles.forEach(role => {
                            generatedHTML += `<span>${role.role.substring(5)}</span>`;
                        }) + "</td>"
                        temp += "<td>" + `<a th:href='@{/admin/users/(id=${u.id})}' class='btn btn-primary'>Edit</a>` + "</td>"
                        temp += "<td><button type='button' class='btn btn-danger' id='open-popup'>Delete</button></td>"
                        temp += "</tr>"
                    })
                    document.getElementById("data").innerHTML = temp;
                }
            }
        )
    })


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
            $('#rolesEdit').val(user.role);
        });
        $('#editModal').modal();
    });
});



