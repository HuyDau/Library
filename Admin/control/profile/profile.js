import { getUserProfile } from "../../api/login.js";
import { isAuthenticated } from "../../api/auth.js";

document.addEventListener("DOMContentLoaded",async function () {
  const loadingElement = document.getElementById("loading");
  if (!isAuthenticated()) {
    window.location.href = "login.html";
  }

  try {
    loadingElement.style.display = "block"
    const data = await getUserProfile();
    const listFullName = document.querySelectorAll(".fullName");
    console.log(listFullName)
    listFullName.forEach(e => {
        e.textContent = data.fullName;
    })

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
