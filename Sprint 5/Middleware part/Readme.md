#  IFTTTRuleHub Middleware

For the IFTTT RuleHub as middleware an SpringBoot application is used. It allowes to execute the IFTTT rules. It can be accessed by any of the end application like mobile application or frontend (ex: Vuejs). When the middleware gets any rules form the end user, it checks for the condition and action. Then it applies the action if the conditions are met. The middleware will be able to connect any service application. It applies the action for metted condition to the service. For our project we added heater service to turn heating off or to turn heating off into comfort mode.

## Getting Started

Follow these steps to get the backend up and running:

### Prerequisites

- Java version 17
- running the iot device (to execute functionalities)

## Test Api using swagger 
#### Feature controllers: rule-controller, action-controller, condition-controller
http://localhost:8080/swagger-ui/#/

The default port is 8080. But it may change if application.properties server port is changed like 8081.
When the port is on 8081 the url:
http://localhost:8081/swagger-ui/#/

- Username: user
- Password: password

## Used Tools:
Application: spring boot </br>
Database: H2 console</br>
Automation tool: gradle</br> 
IDE: intellij idea</br>

### Installation

1. Clone the repository:

```bash
git clone https://gitlab.emse.fr/tareq.chy/integrative-development-of-a-cps2-project.git
``` 

2. Navigate to the project directory:

```
cd /path/Middleware part
```
3. Run the code

This will start the development server at http://localhost:8080 (or another available port). As the server port is set to 8081 in application properties. So, it will run at  http://localhost:8081. There will be a message "Welcome to faircorp!!" By using swagger the api endpoints can be checked.


## Features
- View and manage IFTTT rules
- Get data for temperature, humidity, Date time and CO2 levels
- Rule view, creation, modification, deletion, execution rules
- Action view, creation, modification and deletion
- Condition view, creation, modification and deletion
- Saving data from Iot device
- Connecting with heater service and executing giving action intruction 


### Rule Endpoints
#### Get Rule List:
- Request Type: GET
- Endpoint: /api/rule

#### Create A New Rule:
- Request Type: POST
- Endpoint: /api/rule
- Body:
    ```json
    {
        "active": "true",
        "date": "30/03/2023",
        "name": "RULE 10",
        "time": "13:01"
  }
    ```

#### Get Rule With A Specific ID:
- Request Type: GET
- Endpoint: /api/rule/{rule_id}
- {rule_id}: A Long Integer

#### Get data from IoT device for previous hours:
- Request Type: GET
- Endpoint: /api/rule/data/{hours}

#### Execute Rule:
- Request Type: GET
- Endpoint: /api/rule/execute

#### Delete A Rule:
- Request Type: DELETE
- Endpoint: /api/rule/{rule_id}
- {rule_id}: A Long Integer


### Condition Endpoints
#### Get Condition List:
- Request Type: GET
- Endpoint: /api/condition

#### Create A New Condition:
- Request Type: POST
- Endpoint: /api/condition
- Body:
    ```json
    {
        "conditionType": "string",
        "conditionUnit": "string",
        "conditionValue": 0,
        "name": "string",
        "ruleId": -9
    }
    ```

The ruleId will be of an existing rule

#### Get Condition With A Specific Condition ID:
- Request Type: GET
- Endpoint: /api/condition/{condition_id}
- {condition_id}: A Long Integer

#### Get Condition Rule id:
- Request Type: GET
- Endpoint: /api/condition/rule/{rule_id}

#### Delete A Condition:
- Request Type: DELETE
- Endpoint: /api/condition/{condition_id}
- {condition_id}: A Long Integer

### Action Endpoints
#### Get Action List:
- Request Type: GET
- Endpoint: /api/action

#### Create A New Action:
- Request Type: POST
- Endpoint: /api/action
- Body:
    ```json
    {
        "name": "string",
        "ruleId": -9
    }
    ```

The ruleId will be of an existing rule. The name is the action.

#### Get Action With A Specific Action ID:
- Request Type: GET
- Endpoint: /api/action/{action_id}
- {action_id}: A Long Integer

#### Get Action by Rule id:
- Request Type: GET
- Endpoint: /api/action/rule/{rule_id}

#### Delete An Action:
- Request Type: DELETE
- Endpoint: /api/action/{action_id}
- {action_id}: A Long Integer
