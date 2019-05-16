<!DOCTYPE <!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Page Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" media="screen" href="main.css">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="main.js"></script>
</head>

<body style="background-color:lightblue">
        <div class="p-3 mb-2 bg-info text-white">
    <form action= "/BankAppSpringMVC/app/bank/update" method="post" style="margin-left:500px" >
            <h1 style="margin-left:50px;color: brown;">Update Account</h1>
        <table style="border-block-start-color: black">
            
            <th><h4 style="margin-top:50px; color:darkgreen">Update Details</h4></th>
            <tr>
                    <td><label>Account Id</label></td>
                    <td> <input type="number" name="account_id" value="${accounts.accountId}" readonly="readonly"></td>
            </tr>
            <tr>
                <td><label>Customer Name</label></td>
                <td> <input type="text" name="customer_name" value="${accounts.acccoutHolderName}"></td>
            </tr>
            <tr>
                <td><label>Account Type</label></td>
                <td> 
                    <select name="account_type" >
                        <option value="saving">savings</option>
                        <option value="current">current</option>
                        </select>
                </td>
            </tr>
             <tr>
                <td><label>Account Balance</label></td>
                <td><input type="number" name="account_balance" value="${accounts.accountBalance}" readonly="readonly"></td>
             </tr>   
            <tr><td><input type="submit" value="Update Account" ></td></tr>
        </table>

    </form>
</div>


</body>


<html>