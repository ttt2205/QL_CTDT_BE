<!-- Hướng dẫn chạy -->

B1: Tạo database có tên trùng với tên trong application.properties
B2: Sửa đổi mật khẩu kết nối tới db trong application.properties
B3: chạy lệnh mvn spring-boot:run

<!-- Khi phát triển tính năng mới-->

B1: git checkout develope
B2: git checkout -b feature-<tính năng>-<name>
B3: sau khi làm xong thì: git add . -> git commit -m "<nội dung>"
B4: git push
B5: tạo pull request vào nhánh develope (sẽ có người merge vào sau không tự ý merge)
B6: Sau khi merge xong muốn phát triển tính năng mới thì quay lại bước 1
