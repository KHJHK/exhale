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

export { getProfile };
