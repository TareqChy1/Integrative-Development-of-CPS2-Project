#  IFTTTRuleHub Frontend

This is a simple Vue.js frontend for an IFTTT RuleHub, allowing you to view and manage IFTTT rules. This frontend is designed to work with Node.js 16.x.

## Getting Started

Follow these steps to get the frontend up and running:

### Prerequisites

- Node.js v16.x
- npm (comes with Node.js)

### Installation

1. Clone the repository:

```bash
git clone https://gitlab.emse.fr/tareq.chy/integrative-development-of-a-cps2-project.git
``` 

2. Navigate to the project directory:

```cd <project-directory-path>
```

3. Install the dependencies:

```
npm install
```
## Running the Development Server
- To start the development server, run: 
    ```
    npm run serve
    ```
- This will start the development server at http://localhost:8080 (or another available port). Open this URL in your browser to view and interact with the IFTTT frontend.

## Building for Production
- To build the frontend for production, run:
    ```
    npm run build
    ```
- This will create a dist folder with the compiled frontend assets, which can be deployed to a web server.

## Features
- View and manage IFTTT rules
- Real-time data display for temperature, humidity, and CO2 levels
- Rule creation, modification, and deletion

## Contributing
- Feel free to fork this repository and submit pull requests for any improvements or bug fixes. Please follow the existing code style and ensure that your changes do not introduce new issues.

##License
- This project is licensed under the MIT License. See the LICENSE file for more information.
