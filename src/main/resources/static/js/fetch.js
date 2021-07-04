
const requestURL = 'http://localhost:8080/admin/users/'
const requestRoles = 'http://localhost:8080/admin/users/roles/'
let rolesBuffer

/**
 * Get request.
 * @param url
 * @returns {Promise<any | void>}
 */
function sendGetRequest(url) {
    const headers = {
        'Content-Type': 'application/json'
    }
    return fetch(url, {
        method: 'GET',
        headers: headers
    })
        .then(response => response.json())
        .catch(err => console.error(err))
}

/**
 * POST request.
 * @param url
 * @param method
 * @param body
 * @returns {Promise<*>}
 */
function sendPOSTRequest(url, method, body = null) {
    const headers = {
        'Content-Type': 'application/json'
    }
    return fetch(url, {
        method: "POST",
        body: JSON.stringify(body),
        headers: headers
    }).then(response => {
        if (response.ok) {
            return response.json()
        }
        return response.json().then(error => {
            const e = new Error('Something wrong')
            e.data = error
            throw e
        })
    })
}

/**
 * PUT request.
 * @param url
 * @param method
 * @param body
 * @returns {Promise<*>}
 */
function sendPUTRequest(url, method, body = null) {
    const headers = {
        'Content-Type': 'application/json'
    }
    return fetch(url, {
        method: "PUT",
        body: JSON.stringify(body),
        headers: headers
    }).then(response => {
        if (response.ok) {
            return response.json()
        }
        return response.json().then(error => {
            const e = new Error('Something wrong')
            e.data = error
            throw e
        })
    })
}


/**
 * Delete request.
 * @param url
 * @returns {Promise<void>}
 */
function sendDeleteRequest(url) {
    const headers = {
        'Content-Type': 'application/json',
    }
    return fetch(url, {
        method: 'DELETE',
        headers: headers
    })
        .then(response => console.log(response))
        .catch(err => console.error(err))
}

/**
 *   Insert to table body. Two button and all users.
 * @param data
 */
function appendData(data) {
    let mainContainer = document.getElementById("tableData")
    for (let i = 0; i < data.length; i++) {
        let tableRow = document.createElement("tr")
        tableRow.id = data[i].id
        tableRow.innerHTML =
            "<td>" + data[i].id + "</td>" +
            "<td>" + data[i].username + "</td>" +
            "<td>" + data[i].age + "</td>" +
            "<td>" + rolesString(data[i].roles) + "</td>" +
            "<td><button class='btn btn-warning' data-toggle='modal' onclick='editModal()'>Edit</button></td>" +
            "<td><button class='btn btn-danger' data-toggle='modal' onclick='deleteModal()'>Delete</button></td>"
        mainContainer.appendChild(tableRow)
    }
}

/**
 *To string roles for table .
 * @param roles
 * @returns {string}
 */
function rolesString(roles) {
    let result = '';
    for (let i = 0; i < roles.length; i++) {
        result += roles[i].name + " "
    }
    return result;
}

function documentReady() {
    $(document).ready(() => {
        document.getElementById("addCheckbox").innerHTML = ''
        document.getElementById("editCheckbox").innerHTML = ''
        sendGetRequest(requestURL).then(data => appendData(data))
        sendGetRequest(requestRoles).then(data => {
            rolesBuffer = data
            createCheckbox(data, "addCheckbox")
            createCheckbox(data, "editCheckbox")
        })
    })
}

function createCheckbox(data, elementName) {
    const rolCheckbox = document.getElementById("" + elementName + "")
    for (let i = 0; i < data.length; i++) {
        let label = document.createElement("label")
        label.innerText = rolesBuffer[i].name
        let checkbox = document.createElement("input")
        checkbox.setAttribute("type", "checkbox")
        checkbox.setAttribute("id", rolesBuffer[i].id + elementName)
        rolCheckbox.appendChild(label)
        rolCheckbox.appendChild(checkbox)

        checkbox.addEventListener('change', () => {
            if (checkbox.hasAttribute("checked")) {
                checkbox.removeAttribute("checked")
            } else {
                checkbox.setAttribute("checked", "true")
            }

        })
    }
}

function addModal() {

    clearCheckboxes("addCheckbox")

    $("#addModal").modal('show')
}


function addUser() {
    console.log(document.getElementById(1 + "addCheckbox").hasAttribute("checked"))
    console.log(document.getElementById(2 + "addCheckbox").hasAttribute("checked"))
    let rolesArray = []
    rolesBuffer.forEach(role => {
        console.log("Entry point!")
        if (document.getElementById(role.id + "addCheckbox")
            .hasAttribute("checked")) {
            console.log("CHECKED")
            rolesArray.push({
                id: role.id,
                name: role.name
            })
        }
        console.log(role.id + "addCheckbox")
    })

    console.log(rolesArray)

    const body = {
        username: $("#addUsername").val(),
        age: $("#addAge").val(),
        password: $("#addPassword").val(),
        roles: rolesArray
    }

    sendPOSTRequest(requestURL, 'POST', body).then(data => {
        document.getElementById("tableData").innerHTML = ''
        documentReady()
    })
        .then($("#addModal").modal('hide'))
        .then(formClear)
}


function editModal() {

    // clearCheckboxes("editCheckbox")

    $("#editModal").modal('show')

    let id = event.target.parentNode.parentNode.id

    sendGetRequest(requestURL + id)
        .then(data => {
            console.log(data)
            return data
        })
        .then(data => {
            $("#editID").val(data.id)
            $("#editUsername").val(data.username)
            $("#editAge").val(data.age)
            $("#editPassword").val(data.password)

            rolesBuffer.forEach(roleFromBuffer => {
                data.roles.forEach(role => {
                    if (role.name === roleFromBuffer.name) {
                        document.getElementById(roleFromBuffer.id + "editCheckbox")
                            .setAttribute("checked", "true")
                    }
                })
            })


        })

}

function editUser() {

    let rolesArray = []
    rolesBuffer.forEach(role => {
        if (document.getElementById(role.id + "editCheckbox")
            .hasAttribute("checked")) {
            rolesArray.push({
                id: role.id,
                name: role.name
            })
        }
    })

    const body = {
        id: $("#editID").val(),
        username: $("#editUsername").val(),
        age: $("#editAge").val(),
        password: $("#editPassword").val(),
        roles: rolesArray
    }

    sendPUTRequest(requestURL, 'PUT', body).then(data => {
        document.getElementById("tableData").innerHTML = ''
        documentReady()
    })
        .then($("#editModal").modal('hide'))

}

function deleteUser() {
    sendDeleteRequest(requestURL + $("#deleteID").val())
        .then(data => {
            document.getElementById("tableData").innerHTML = ''
            documentReady()
        })
        .then($("#deleteModal").modal('hide'))
}

function deleteModal() {

    let id = event.target.parentNode.parentNode.id

    $("#deleteModal").modal("show")
    sendGetRequest(requestURL + id)
        .then(data => {
            console.log(data)
            return data
        })
        .then(data => {
            $("#deleteID").val(data.id)
            $("#deleteUsername").val(data.username)
            $("#deleteAge").val(data.age)
            $("#deletePassword").val(data.password)

        })


}

function clearCheckboxes(elementName) {
    rolesBuffer.forEach(role => {
        document.getElementById(role.id + elementName)
            .removeAttribute("checked")
    })
}


function formClear() {
    $("#addUsername").val("");
    $("#addAge").val("");
    $("#addPassword").val("");

}

documentReady()