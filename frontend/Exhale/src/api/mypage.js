import axios from "axios";

const baseURL = import.meta.env.VITE_BASE_URL + "/api/";
const token = localStorage.getItem("JWT_token");

const getProfile = () => {
  return axios.get(baseURL + "users/profile", {
    headers: {
      Authorization: token,
    },
  });
};

const checkPassword = (password) => {
  const data = {
    password: password,
  };

  return axios.post(baseURL + "users/check-password", data, {
    headers: {
      Authorization: token,
    },
  });
};

const rePassword = (old_password, new_password) => {
  const data = {
    old_password: old_password,
    new_password: new_password,
  };

  return axios.post(baseURL + "users/repassword", data, {
    headers: {
      Authorization: token,
    },
  });
};

export { getProfile, checkPassword, rePassword };
