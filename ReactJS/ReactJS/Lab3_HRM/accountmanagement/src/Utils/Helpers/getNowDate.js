// Hàm lấy ngày tháng hiện tại
export function getNowDate() {
  let date = new Date();
  // Lấy ra ngày hiện tại
  let currentDay = date.getDate();
  // Lấy ra tháng hiện tại
  let currentMonth = date.getMonth() + 1;
  // Lấy ra năm hiện tại
  var currentYear = date.getFullYear();


  //   Trả về định dạng: yyyy-MM-dd
  return currentYear + "-" + currentMonth + "-" + currentDay;
}