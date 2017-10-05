## API規格書

URL | MEMO
---| ---| 
User/Login|登入(OK)
User/ThreePartLogin|第三方登入
User/SignUp|註冊(OK)
User/UpdatePassword|修改密碼(OK)
User/UpdatePicture|修改大頭貼(OK)
User/ForgetPassword|忘記密碼(OK)
User/VerifyCode|驗證驗證碼(OK)
User/ResetPassword|重製密碼(OK)
Picture/Create|新增圖片(OK)
Friend/Search|搜尋好友(OK)
Friend/Create|新增好友(OK)
Friend/Delete|刪除好友(OK)
Friend/GetFriendList|取得好友列表(OK)
Friend/GetStrangerList|取得陌生人列表
FriendChat/Create|新增好友聊天
FriendChat/GetList|取得好友聊天列表
PersonalJourney/Create|新增個人旅程(OK)
PersonalJourney/Update|修改個人旅程
PersonalJourney/Start|開始個人旅程(OK)
PersonalJourney/End|結束個人旅程(OK)
PersonalJourney/GetList|取得個人旅程列表(OK)
PersonalJourney/GetPublicList|取得公開個人旅程列表(OK)
PersonalJourney/Get|取得個人旅程(OK)
PersonalJourneyComment/Create|新增個人旅程評論(OK)
PersonalJourneyComment/GetList|取得個人旅程評論列表
PersonalJourneyDetail/Create|新增個人旅程細節
PersonalJourneyDetail/GetAll|取得個人旅程所有細節
Group/Create|新增群組(OK)
Group/Update|修改群組
Group/GetList|取得群組列表(OK)
Group/Get|取得群組(OK)
GroupUser/Create|新增群組使用者(OK)
GroupUser/Update|修改群組使用者
GroupUser/Delete|刪除群組使用者(OK)
GroupChat/Create|新增群組聊天(OK)
GroupChat/GetList|取得群組聊天列表(OK)
GroupJourney/Create|新增群組旅程
GroupJourney/Update|修改群組旅程
GroupJourney/GetList|取得群組旅程列表
GroupJourney/Get|取得群組旅程
GroupJourneyDetail/Create|新增群組旅程細節
GroupJourneyDetail/GetAll|取得群組旅程所有細節

## 錯誤回傳範例
```json
{
    "result": 0,
    "message": "message"
}
```

## User/Login(登入)
request:
```json
{
    "userId": "userId",
    "password": "password"
}
```
response:
```json
{
    "result": 1,
    "userId": "userId",
    "userName": "userName",
    "userPicture": "userPicture",
    "email": "email",
    "modifyDate": "modifyDate"
}
```

## User/ThreePartLogin(第三方登入)
request:
```json
{
    "userId": "userId",
    "userName": "userName",
    "signInOrigin": "google/facebook"
}
```
response:
```json
{
    "result": 1,
    "userId": "userId",
    "userName": "userName",
    "userPicture": "userPicture",
    "email": "email",
    "modifyDate": "modifyDate"
}
```

## User/SignUp(註冊)
request:
```json
{
    "userId": "userId",
    "password": "password",
    "userName": "userName",
    "email": "email"
}
```
response:
```json
{
    "result": 1
}
```

## User/UpdatePassword(修改密碼)
request:
```json
{
    "userId": "userId",
    "oldPassword": "oldPassword",
    "newPassword": "newPassword"
}
```
response:
```json
{
    "result": 1,
}
```

## User/UpdatePicture(修改大頭貼)
request:
```json
{
    "userId": "userId",
    "picturePath": "picturePath"
}
```
response:
```json
{
    "result": 1,
}
```

## User/ForgetPassword(忘記密碼)
request:
```json
{
    "userId": "userId",
    "email": "email"
}
```
response:
```json
{
    "result": 1,
}
```

## User/VerifyCode(驗證驗證碼)
request:
```json
{
    "userId": "userId",
    "verificationCode": "verificationCode"
}
```
response:
```json
{
    "result": 1
}
```

## User/VerifyCode(驗證驗證碼)
request:
```json
{
    "userId": "userId",
    "verificationCode": "verificationCode"
}
```
response:
```json
{
    "result": 1
}
```

## User/ResetPassword(重製密碼)
request:
```json
{
    "userId": "userId",
    "verificationCode": "verificationCode",
    "newPassword": "newPassword"
}
```
response:
```json
{
    "result": 1
}
```


## Picture/Create(新增圖片)(form-data)
request : 
```
"file": "file",
"fileName": "fileName",
"subFileName": "subFileName",
"type": "0(user)/1(personalJourney)/2(groupJourney)"

```
response:
```json
{
    "result": 1,
    "picturePath": "picturePath"
}
```

## Friend/Search(搜尋好友)
request:
```json
{
    "userName": "userName"
}
```
response:
```json
{
    "result": 1,
    "friends": [
        {
            "userId": "userId",
            "userName": "userName",
            "userPicture": "userPicture"
        }
    ]
}
```

## Friend/Create(新增好友)
request:
```json
{
    "userId": "userId",
    "friendId": "friendId"
}
```
response:
```json
{
    "result": 1
}
```

## Friend/Delete(刪除好友)
request:
```json
{
    "userId": "userId",
    "friendId": "friendId"
}
```
response:
```json
{
    "result": 1
}
```

## Friend/GetFriendList(取得好友列表)
request:
```json
{
    "userId": "userId"
}
```
response:
```json
{
    "result": 1,
    "friends": [
        {
            "userId": "userId",
            "userName": "userName",
            "userPicture": "userPicture"
        }
    ]
}
```

## Friend/GetStrangerList(取得陌生人列表)
request:
```json
{
    "userId": "userId"
}
```
response:
```json
{
    "result": 1,
    "strangers": [
        {
            "userId": "userId",
            "userName": "userName",
            "userPicture": "userPicture"
        }
    ]
}
```

## FriendChat/Create(新增好友聊天)
request:
```json
{
    "userId": "userId",
    "friendId": "friendId",
    "content": "content"
}
```
response:
```json
{
    "result": 1
}
```

## FriendChat/GetList(取得好友聊天列表)
request:
```json
{
    "userId": "userId",
    "friendId": "friendId",
    "lastChatId": 1
}
```
response:
```json
{
    "result": 1,
    "chats": [
        {
            "chatId": 1,
            "userId": "userId",
            "userName": "userName",
            "userPicture": "userPicture",
            "friendId": "friendId",
            "friendName": "friendName",
            "friendPicture": "friendPicture",
            "content": "content",
            "createDate": "2017-01-01 00:00:00",
        }
    ]
}
```

## PersonalJourney/Create(新增個人旅程)
request:
```json
{
    "userId": "userId",
    "name": "name",
    "content": "content",
    "points": "points",
    "status": "0",
    "isOpen": "0",
    "startTime": "2017-01-01",
    "endTime": "2017-01-01",
    "pictures":["path", "path"]
}
```
response:
```json
{
    "result": 1
}
```

## PersonalJourney/Update(修改個人旅程)
request:
```json
{
    "personalJourneyId": 1,
    "name": "name",
    "content": "content"
}
```
response:
```json
{
    "result": 1
}
```

## PersonalJourney/Start(開始個人旅程)
request:
```json
{
    "personalJourneyId": 1,
    "startTime": "2017-01-01 00:00:00"
}
```
response:
```json
{
    "result": 1
}
```

## PersonalJourney/End(結束個人旅程)
request:
```json
{
    "personalJourneyId": 1,
    "endTime": "2017-01-01 00:00:00",
    "routes": [
        {
            "latitude": 123.123123,
            "longitude": 123.123123,
            "time": "2017-01-01 00:00:00",
        }
    ]
}
```
response:
```json
{
    "result": 1
}
```

## PersonalJourney/GetList(取得個人旅程列表)
request:
```json
{
    "userId": "userId"
}
```
response:
```json
{
    "result": 1,
    "personalJourneys": [
        {
            "personalJourneyId": 1,
            "name": "name",
            "content": "content",
            "status": "0",
            "createDate": "2017-01-01 00:00:00",
            "pictures":["path", "path"]
        }
    ]
}
```

## PersonalJourney/GetPublicList(取得公開個人旅程列表)
request:
```json
{
    
}
```
response:
```json
{
    "result": 1,
    "personalJourneys": [
        {
            "personalJourneyId": 1,
            "userName": "userName",
            "name": "name",
            "content": "content",
            "modifyDate": "2017-01-01 00:00:00",
            "pictures":[
                "path"
            ]
        }
    ]
}
```

## PersonalJourney/Get(取得個人旅程)
request:
```json
{
    "personalJourneyId": 1
}
```
response:
```json
{
    "result": 1,
    "personalJourneyId": 1,
    "userId": "userId",
    "userName": "userName",
    "userPicture": "userPicture",
    "name": "name",
    "content": "content",
    "status": "0",
    "isOpen": "0",
    "startTime": "2017-01-01 00:00:00",
    "endTime": "2017-01-01 00:00:00",
    "createDate": "2017-01-01 00:00:00",
    "modifyDate": "2017-01-01 00:00:00",
    "pictures":[
        "path"
    ],
    "comments": [
        {
            "userId": "userId",
            "userName": "userName",
            "userPicture": "userPicture",
            "comment": "comment",
            "createDate": "2017-01-01 00:00:00"
        }
    ]
}
```

## PersonalJourneyComment/Create(新增個人旅程評論)
request:
```json
{
    "personalJourneyId": 1,
    "userId": "userId",
    "comment": "comment"
}
```
response:
```json
{
    "result": 1
}
```

## PersonalJourneyDetail/Create(新增個人旅程細節)
request:
```json
{
    "personalJourneyId": 1,
    "personalJourneyDetails": [
        {
            "latitude": 123.123,
            "longitude": 123.123
        }
    ]
}
```
response:
```json
{
    "result": 1
}
```

## PersonalJourneyDetail/GetAll(取得個人旅程所有細節)
request:
```json
{
    "personalJourneyId": 1
}
```
response:
```json
{
    "result": 1,
    "personalJourneyDetails": [
        {
            "personalJourneyDetailId": 1,
            "latitude": 123.123,
            "longitude": 123.123,
            "createDate": "2017-01-01 00:00:00"
        }
    ]
}
```

## Group/Create(新增群組)
request:
```json
{
    "userId": "userId",
    "name": "name",
    "picturePath": "picturePath",
    "members": [
        "userId",
        "userId"
    ]
}
```
response:
```json
{
    "result": 1
}
```

## Group/Update(修改群組)
request:
```json
{
    "groupId": 1,
    "userId": "userId",
    "name": "name",
    "picturePath": "picturePath"
}
```
response:
```json
{
    "result": 1
}
```

## Group/GetList(取得群組列表)
request:
```json
{
    "userId": "userId"
}
```
response:
```json
{
    "result": 1,
    "groups": [
        {
            "groupId": 1,
            "name": "name",
            "picturePath": "picturePath"
        }
    ]
}
```

## Group/Get(取得群組)
request:
```json
{
    "groupId": 1
}
```
response:
```json
{
    "result": 1,
    "groupId": "",
    "name": "name",
    "picturePath": "picturePath",
    "members": [
        {
            "userId": "userId",
            "userName": "userName",
            "userPicture": "userPicture",
            "groupRoleId": "groupRoleId",
            "groupRoleName": "groupRoleName",
        }
    ]
}
```

## GroupUser/Create(新增群組使用者)
request:
```json
{
    "groupId": 1,
    "userId": "userId",
    "members":[
        {
            "userId": "abc"
        }
    ]
}
```
response:
```json
{
    "result": 1
}
```

## GroupUser/Update(修改群組使用者)
request:
```json
{
    "groupId": 1,
    "userId": "userId",
    "targetUserId": "targetUserId",
    "groupRoleId": "groupRoleId"
}
```
response:
```json
{
    "result": 1
}
```

## GroupUser/Delete(刪除群組使用者)
request:
```json
{
    "groupId": 1,
    "userId": "userId",
    "targetUserId": "targetUserId"
}
```
response:
```json
{
    "result": 1
}
```

## GroupChat/Create(新增群組聊天)
request:
```json
{
    "groupId": 1,
    "userId": "userId",
    "content": "content",
    "lastChatId": 1
}
```
response:
```json
{
    "result": 1,
    "groupChats": [
        {
            "groupChatId": 1,
            "groupId": 1,
            "userId": "userId",
            "userName": "userName",
            "userPicture": "userPicture",
            "content": "content",
            "createDate": "2017-01-01 00:00:00"
        }
    ]
}
```

## GroupChat/GetList(取得群組聊天列表)
request:
```json
{
    "groupId": 1,
    "lastChatId": 1
}
```
response:
```json
{
    "result": 1,
    "groupChats": [
        {
            "groupChatId": 1,
            "groupId": 1,
            "userId": "userId",
            "userName": "userName",
            "userPicture": "userPicture",
            "content": "content",
            "createDate": "2017-01-01 00:00:00"
        }
    ]
}
```

## GroupJourney/Create(新增群組旅程)
request:
```json
{
    "groupId": 1,
    "userId": "userId",
    "name": "name",
    "content": "content",
    "points": "points",
    "status": "0",
    "startTime": "2017-01-01 00:00:00",
    "endTime": "2017-01-01 00:00:00"
}
```
response:
```json
{
    "result": 1
}
```

## GroupJourney/Update(修改群組旅程)
request:
```json
{
    "groupJourneyId": 1,
    "userId": "userId",
    "name": "name",
    "content": "content",
    "points": "points",
    "status": "0",
    "startTime": "2017-01-01 00:00:00",
    "endTime": "2017-01-01 00:00:00"
}
```
response:
```json
{
    "result": 1
}
```

## GroupJourney/GetList(取得群組旅程列表)
request:
```json
{
    "groupId": 1
}
```
response:
```json
{
    "result": 1,
    "groupJourneys": [
        {
            "groupJourneyId": 1,
            "name": "name",
            "content": "content",
            "status": "0",
            "createDate": "2017-01-01 00:00:00"
        }
    ]
}
```

## GroupJourney/Get(取得群組旅程)
request:
```json
{
    "groupJourneyId": 1
}
```
response:
```json
{
    "result": 1,
    "groupJourneyId": 1,
    "name": "name",
    "content": "content",
    "status": "0",
    "isOpen": "0",
    "startTime": "2017-01-01 00:00:00",
    "endTime": "2017-01-01 00:00:00",
    "createDate": "2017-01-01 00:00:00",
    "modifyDate": "2017-01-01 00:00:00"
}
```

## GroupJourneyDetail/Create(新增群組旅程細節)
request:
```json
{
    "groupJourneyId": 1,
    "userId": "userId",
    "groupJourneyDetails": [
        {
            "latitude": 123.123,
            "longitude": 123.123
        }
    ]
}
```
response:
```json
{
    "result": 1
}
```

## GroupJourneyDetail/GetAll(取得群組旅程所有細節)
request:
```json
{
    "groupJourneyId": 1
}
```
response:
```json
{
    "result": 1,
    "members": [
        {
            "userId": "userId",
            "userName": "userName",
            "userPicture": "userPicture",
            "groupJourneyDetails": [
                {
                    "latitude": 123.123,
                    "longitude": 123.123,
                    "createDate": "2017-01-01 00:00:00"
                }
            ]
        }
    ]
}
```