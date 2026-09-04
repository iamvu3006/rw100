import { api } from "./api";

const getListPositionAPI = () => {
  return api("GET", "possitions/", null);
};

// export
export { getListPositionAPI };
