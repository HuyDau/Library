import api from "./axios-config.js";
export const loginUser = async (email, password) => {
    try {
        const response = await api.post('auth/login', { email, password });
        return response.data;
    } catch (error) {
        console.error('Lỗi khi gọi API login:', error.response ? error.response.data : error.message);
        throw error.response ? error.response.data : new Error('Đã xảy ra lỗi không xác định.');
    }
};


export const getUserProfile = async () => {
    try {
        const response = await api.get('v1/users/me');
        return response.data;
    } catch (error) {
        console.error('Lỗi khi gọi API login:', error.response ? error.response.data : error.message);
        throw error.response ? error.response.data : new Error('Đã xảy ra lỗi không xác định.');
    }
}