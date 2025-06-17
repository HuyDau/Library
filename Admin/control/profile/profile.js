import { getUserProfile } from "../../api/login.js";
import { isAuthenticated } from "../../api/auth.js";

document.addEventListener("DOMContentLoaded",async function () {
  const loadingElement = document.getElementById("loading");
  if (loadingElement) {
    loadingElement.style.display = "none"; // Ẩn loading khi trang tải xong
  }
  if (!isAuthenticated()) {
    window.location.href = "login.html";
  }

  try {
    const data = await getUserProfile();
    document.querySelectorAll(".fullName").textContent = data.fullName || '';
    console.log(document.querySelectorAll(".fullName"))
    document.getElementById("email").textContent = data.email || '';
    document.getElementById("phoneNumber").textContent = data.phoneNumber || '';
  } catch (error) {
    console.error(error);
  }
});
