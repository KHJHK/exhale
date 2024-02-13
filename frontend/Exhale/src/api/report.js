import axios from "axios";

const baseURL = import.meta.env.VITE_BASE_URL + "/api/";
const token = localStorage.getItem("JWT_token");

const getLetterData = () => {
  return axios.get(baseURL + "statistics/letter", {
    headers: {
      Authorization: token,
    },
  });
};

const getSolvedData = () => {
  return axios.get(baseURL + "statistics/solved-data", {
    headers: {
      Authorization: token,
    },
  });
};

export { getLetterData, getSolvedData };
