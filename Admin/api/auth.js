const TOKEN_KEY = 'authToken';
export const setAuthToken = (token) => {
    localStorage.setItem(TOKEN_KEY, token);
};
export const getAuthToken = () => {
    return localStorage.getItem(TOKEN_KEY);
};
export const removeAuthToken = () => {
    localStorage.removeItem(TOKEN_KEY);
};
export const isAuthenticated = () => {
    const token = getAuthToken();
    return !!token;
};
export const redirectToLogin = () => {
    if (window.location.pathname !== '/index.html' && window.location.pathname !== '/') {
        window.location.href = 'login.html';
    }
};
export const checkAuthAndRedirect = () => {
    if (!isAuthenticated()) {
        redirectToLogin();
    }
};
export const handleLogout = () => {
    removeAuthToken();
    redirectToLogin();
};