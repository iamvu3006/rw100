## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Chạy chương trình quản lý tài khoản

Chạy các lệnh sau từ thư mục `demo-buoi4` (thư mục chứa `src`). Không biên
dịch riêng `App.java` từ thư mục `src/com/vti/frontend`, vì các class trong
`com.vti.entity` và `com.vti.backend` cũng cần được biên dịch cùng:

```powershell
chcp 65001
javac -encoding UTF-8 -d bin src/com/vti/entity/*.java src/com/vti/backend/*.java src/com/vti/frontend/App.java
java -cp bin com.vti.frontend.App
```

Trong VS Code, hãy mở thư mục `demo-buoi4` làm workspace rồi chạy
`com.vti.frontend.App` bằng nút Run/Debug.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
