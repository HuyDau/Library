import { loginUser } from "../../api/login.js";
import { setAuthToken, isAuthenticated } from "../../api/auth.js";

document.addEventListener('DOMContentLoaded', () => {
    const loadingElement = document.getElementById('loading');
    if (loadingElement) {
        loadingElement.style.display = 'none'; // Ẩn loading khi trang tải xong
    }

    if (isAuthenticated()) {
        window.location.href = 'index.html';
    }
    const loginForm = document.getElementById('loginForm');
    const usernameInput = document.getElementById('username');
    const passwordInput = document.getElementById('password');
    const errorMessage = document.getElementById('errorMessage');

    loginForm.addEventListener('submit', async (e) => {
        e.preventDefault(); // Ngăn chặn hành vi submit mặc định của form

        const username = usernameInput.value;
        const password = passwordInput.value;

        errorMessage.textContent = ''; 
        errorMessage.style.display = 'none';

        const loginButton = loginForm.querySelector('button[type="submit"]');
        loginButton.disabled = true;
        loginButton.querySelector('.text').textContent = 'Đang đăng nhập...'; 

        try {
            const data = await loginUser(username, password);
            if (data && data.token) {
                setAuthToken(data.token);
                window.location.href = 'index.html';
            } else {
                throw new Error('Phản hồi API không chứa token.');
            }
        } catch (error) {
            console.error('Lỗi đăng nhập:', error);
            let displayMessage = 'Đã xảy ra lỗi khi đăng nhập.';
            if (error.response && error.response.data && error.response.data.message) {
                displayMessage = error.response.data.message;
            } else if (error.message) {
                displayMessage = error.message;
            }

            errorMessage.textContent = displayMessage;
            errorMessage.style.display = 'block';
        } finally {
            loginButton.disabled = false;
            loginButton.querySelector('.text').textContent = 'Log In';
        }
    })
})