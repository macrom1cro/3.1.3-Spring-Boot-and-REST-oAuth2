let tableUsers = [];

fetch("http://localhost:8080/admin/users").then(
    res => {
        res.json().then(
            data => {
                if (data.length > 0) {
                    data.forEach((user) => {
                        tableUsers.push(user)
                    })
                    console.log(tableUsers);
                    showUsers(tableUsers);
                }
            }
        )
    })

function showUsers(event) {
    let temp = "";
    console.log(event);
    event.forEach((user) => {
        temp += "<tr>"
        temp += "<td>" + user.id + "</td>"
        temp += "<td>" + user.firstName + "</td>"
        temp += "<td>" + user.lastName + "</td>"
        temp += "<td>" + user.age + "</td>"
        temp += "<td>" + user.email + "</td>"
        let temp2 = "";
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


function rolesUser(event) {
    let rolesAdmin = {};
    let rolesUser = {};
    let roles = [];
    let allRoles = [];
    let sel = document.querySelector(event);
    for (let i = 0, n = sel.options.length; i < n; i++) {
        if (sel.options[i].selected) {
            roles.push(sel.options[i].value);
        }
    }
    if (roles.includes("ROLE_ADMIN")) {
        rolesAdmin["id"] = 2;
        rolesAdmin["role"] = "ROLE_ADMIN";
        allRoles.push(rolesAdmin);
    }
    if (roles.includes("ROLE_USER")) {
        rolesUser["id"] = 1;
        rolesUser["role"] = "ROLE_USER";
        allRoles.push(rolesUser);
    }
    return allRoles;
}

document.getElementById('newUser').addEventListener('submit', submitFormNewUser);

function submitFormNewUser(event) {
    event.preventDefault();
    let formData = new FormData(event.target);
    let user = {};
    formData.forEach((value, key) => user[key] = value);
    user["roles"] = rolesUser("#roles");
    let request = new Request(event.target.action, {
        method: 'POST',
        body: JSON.stringify(user),
        headers: {
            'Content-Type': 'application/json',
        },
    });

    fetch(request).then(
        res => {
            res.json().then(
                newUser => {
                    tableUsers.push(newUser);
                    showUsers(tableUsers);
                })
        });
    $('#myTab li:first-child a').tab('show');
}

document.getElementById('editUser').addEventListener('submit', submitFormEditUser);

function submitFormEditUser(event) {
    event.preventDefault();
    let formData = new FormData(event.target);
    let user = {};
    formData.forEach((value, key) => user[key] = value);
    user["roles"] = rolesUser("#rolesEdit");
    let request = new Request("http://localhost:8080/admin/users/" + user["id"], {
        method: 'PUT',
        body: JSON.stringify(user),
        headers: {
            'Content-Type': 'application/json',
        },
    });
    fetch(request).then(
        function (response) {
            console.log(response);
            delete user["_method"];
            let indexEditUser = tableUsers.findIndex(item => item.id == user["id"]);
            tableUsers.splice(indexEditUser,1,user)
            showUsers(tableUsers);
        },
        function (error) {
            console.error(error);
        }
    );

    $('#editModal').modal('hide');

}

document.getElementById('deleteUser').addEventListener('submit', submitFormDeleteUser);

function submitFormDeleteUser(event) {
    event.preventDefault();
    let formData = new FormData(event.target);
    let user = {};
    formData.forEach((value, key) => user[key] = value);
    let request = new Request("http://localhost:8080/admin/users/" + user["id"], {
        method: 'DELETE',
        body: JSON.stringify(user),
        headers: {
            'Content-Type': 'application/json',
        },
    });
    fetch(request).then(
        function (response) {
            console.log(response);
            let deleteUser = tableUsers.find(item => item.id == user["id"]);
            tableUsers = tableUsers.filter(function (user) {
                return user !== deleteUser;
            })
            showUsers(tableUsers);
        },
        function (error) {
            console.error(error);
        }
    );
    console.log('Запрос request отправляется');
    $('#deleteModal').modal('hide');

}

$(document).ready(function () {
    $(document).on('click', '#delete', function (event) {
        event.preventDefault();
        let href = $(this).attr('href');
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
        let href = $(this).attr('href');
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

