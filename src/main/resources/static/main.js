fetch("http://localhost:8080/admin/users").then(
    res => {
        res.json().then(
            data => {
                console.log(data);
                if (data.length > 0) {
                    var temp = "";
                    data.forEach((user) => {
                        temp += "<tr>"
                        temp += "<td>" + user.id + "</td>"
                        temp += "<td>" + user.firstName + "</td>"
                        temp += "<td>" + user.lastName + "</td>"
                        temp += "<td>" + user.age + "</td>"
                        temp += "<td>" + user.email + "</td>"

                        var temp2 = "";
                        JSON.parse(JSON.stringify(user.roles)).forEach((role) => {
                            temp2 += role.role.substring(5) + ' ';
                        })

                        temp += "<td>" + temp2 + "</td>"
                        temp += "<td>" + `<a href="/admin/users/${user.id}" class="btn btn-primary" id="edit">Edit</a>` + "</td>"
                        temp += "<td>" + `<a href="/admin/users/${user.id}" class="btn btn-danger" id="delete">Delete</a>` + "</td>"
                        temp += "</tr>"
                    })
                    document.getElementById("data").innerHTML = temp;
                }
            }
        )
    })

$(document).ready(function () {
    $(document).on('click', '#addNewUser', function () {
        $("#newUser").submit(function(e) {
            e.preventDefault();
            var data = $(this).serializeArray();
            console.log(data);
            $.post("http://localhost:8080/admin/users", data);
        });
        // $.post("http://localhost:8080/admin/users", {json_string: JSON.stringify({name: "John", time: "2pm"})});
    })
})

$(document).ready(function () {
    $(document).on('click', '#delete', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (user, status) {
            $('#idDelete').val(user.id);
            $('#firstNameDelete').val(user.firstName);
            $('#lastNameDelete').val(user.lastName);
            $('#ageDelete').val(user.age);
            $('#emailDelete').val(user.email);
            $('#rolesDelete').val(user.role);
        });
        $('#deleteModal').modal();
    });
});

$(document).ready(function () {
    $(document).on('click', '#edit', function (event) {
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

