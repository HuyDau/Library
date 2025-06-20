import api from "./axios-config.js";

export const getListUsers = async (params) => {
    try {
        const response = await api.get('v1/users', { params });
        return response.data;
    } catch (error) {
        console.error('Lỗi khi gọi API login:', error.response ? error.response.data : error.message);
        throw error.response ? error.response.data : new Error('Đã xảy ra lỗi không xác định.');
    }
}

export const changePasswordApi = async (id, newPassword) => {
    const params ={
        userId: id,         
        password: newPassword
    }
    return await api.put(`v1/users/${id}`, params);
}