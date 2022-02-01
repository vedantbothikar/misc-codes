// SPDX-License-Identifier: MIT

pragma solidity ^0.6.0;

import "./simpleStorage.sol";

// is means that this contract is inherited from the simpleStorage contract
// so this contract will have all the functions of the simpleStorage contract
contract storageFactory is simpleStorage {

    simpleStorage[] public simpleStorageArray;

    function createSimpleStorageContract() public {
        simpleStorage simpleStorageContract = new simpleStorage();
        simpleStorageArray.push(simpleStorageContract);
    }

    function sfStore(uint256 myContractIndex, uint256 myFavNum) public {

        simpleStorage(address(simpleStorageArray[myContractIndex])).store(myFavNum);
        
    }

    function sfGet(uint256 myContractIndex) public view returns(uint256) {
        return simpleStorage(address(simpleStorageArray[myContractIndex])).retrieve();
    }


}