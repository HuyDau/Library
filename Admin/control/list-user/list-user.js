import { isAuthenticated } from "../../api/auth.js";
import { getListUsers } from "../../api/user.js";


const displayUsersInTable = (usersArray) =>{
    const tableBody = document.getElementById("userTableBody");
    tableBody.innerHTML = "";




    if (!usersArray || usersArray.length === 0) {
        tableBody.innerHTML = '<tr><td colspan="5" class="text-center">Không có người dùng nào.</td></tr>';
        return;
    }

    usersArray.forEach((user, i) => {
        const row = document.createElement('tr');

        const sttCell = document.createElement('td');
        sttCell.textContent = i + 1;
        row.appendChild(sttCell);


        const fullNameCell = document.createElement('td');
        fullNameCell.textContent = user.fullName;
        row.appendChild(fullNameCell);

        const emailCell = document.createElement('td');
        emailCell.textContent = user.email;
        row.appendChild(emailCell);

        const phoneNumberCell = document.createElement('td');
        phoneNumberCell.textContent = user.phoneNumber;
        row.appendChild(phoneNumberCell);

        const roleCell = document.createElement('td');
        roleCell.textContent = user.role;
        row.appendChild(roleCell);

        tableBody.appendChild(row);

    })
}



document.addEventListener("DOMContentLoaded", async function () {
  const loadingElement = document.getElementById("loading");
  if (!isAuthenticated()) {
    window.location.href = "login.html";
  }

  try {
    loadingElement.style.display = "block";
    const params = {
      includeTotal: true,
    };
    const data = await getListUsers(params);
    const users = data.users
    console.log(users)
    displayUsersInTable(users); 
    
  } catch (error) {
    console.error(error);
  } finally {
    loadingElement.style.display = "none";
  }
});
