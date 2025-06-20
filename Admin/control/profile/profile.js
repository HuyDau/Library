import { getUserProfile } from "../../api/login.js";
import { isAuthenticated } from "../../api/auth.js";
import { changePasswordApi } from "../../api/user.js";

var userId;

document.addEventListener("DOMContentLoaded",async function () {
  const loadingElement = document.getElementById("loading");
  if (!isAuthenticated()) {
    window.location.href = "login.html";
  }

  try {
    loadingElement.style.display = "block"
    const data = await getUserProfile();
    const listFullName = document.querySelectorAll(".fullName");
    listFullName.forEach(e => {
        e.textContent = data.fullName;
    })
    userId = data.id
    document.getElementById("email").textContent = data.email || '';
    document.getElementById("phoneNumber").textContent = data.phoneNumber || '';

    const userAvatarTextElement = document.getElementById("userAvatarText");
    if (userAvatarTextElement && data.fullName) {
      const firstChar = data.fullName.charAt(0).toUpperCase();
      userAvatarTextElement.textContent = firstChar;
      function stringToHslColor(str, s, l) {
        let hash = 0;
        for (let i = 0; i < str.length; i++) {
          hash = str.charCodeAt(i) + ((hash << 5) - hash);
        }
        const h = hash % 360;
        return `hsl(${h}, ${s}%, ${l}%)`;
      }

      userAvatarTextElement.style.backgroundColor = stringToHslColor(data.fullName, 70, 50 );
    }
  } catch (error) {
    console.error(error);
  }finally{
    loadingElement.style.display = "none"; 
  }
});


function openPopupChangePassword() {
    const popupChangePassword = document.getElementById('popupOverlay');
    popupChangePassword.classList.add('active');
}
window.openPopupChangePassword = openPopupChangePassword;


function closePopupChangePassword(){
  const popupChangePassword = document.getElementById('popupOverlay');
  popupChangePassword.classList.remove('active');
}
window.closePopupChangePassword = closePopupChangePassword;

function displaySimpleMessage(message, type = 'success', duration = 2000) {
    const messageElement = document.getElementById('simpleMessage');
    if (!messageElement) return;

    messageElement.textContent = message;
    // Xóa các loại cũ và thêm loại mới
    messageElement.classList.remove('success', 'error', 'warning');
    messageElement.classList.add(type);
    messageElement.classList.add('show'); // Hiện thông báo

    setTimeout(() => {
        messageElement.classList.remove('show'); // Ẩn thông báo sau duration
    }, duration);
}

async  function changePassword (){
  const newPassword = document.getElementById('newPassword')
  const confirmPassword = document.getElementById('confirmPassword')

  if(!newPassword.value.trim()){
    displaySimpleMessage('Please input new password!', 'warning', 2000);
    return;
  }else if(!confirmPassword.value.trim()){
    displaySimpleMessage('Please input confirm password!', 'warning', 2000);
    return;
  }else if(newPassword.value.trim() !== confirmPassword.value.trim()){
    displaySimpleMessage("New Password must equals Confirm Password!", 'warning', 2000)
    return
  }

  if (!userId) {
    displaySimpleMessage(
      "Lỗi: ID người dùng không khả dụng. Vui lòng tải lại trang.",
      "error",
      3000
    );
    return;
  }

  try{
      const response = await changePasswordApi(userId, confirmPassword.value.trim())
      if(response){
        newPassword.value = "";
        confirmPassword.value = "";
        closePopupChangePassword();
        displaySimpleMessage('Success!', 'success', 2000);
        
      }else{
        displaySimpleMessage('Change Password Fail: ' + (response && response.data ? response.data.message : 'Error.'), 'error', 3000);
      }
    }catch(err){
      alert(err)
    }
} 
window.changePassword = changePassword