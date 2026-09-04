import { api } from "./api";

const getListDepartmentAPI = () => {
  return api("GET", "departments/", null);
};

// export
export { getListDepartmentAPI };
