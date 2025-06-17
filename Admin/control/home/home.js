import {isAuthenticated } from "../../api/auth.js";

document.addEventListener('DOMContentLoaded', () => {
    if (!isAuthenticated()) {
        window.location.href = 'login.html';
    }
})