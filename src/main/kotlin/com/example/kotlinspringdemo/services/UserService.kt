package com.example.kotlinspringdemo.services

import com.example.kotlinspringdemo.domain.mappers.UserMapper
import com.example.kotlinspringdemo.domain.dto.users.UserDetailsDTO
import com.example.kotlinspringdemo.domain.dto.users.UserDetailsRegistrationDTO
import com.example.kotlinspringdemo.exceptions.*
import com.example.kotlinspringdemo.repository.UserRepo
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepo: UserRepo,
    private val userMapper: UserMapper,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {

    fun createUser(userDetailsRegistrationDTO: UserDetailsRegistrationDTO): UserDetailsDTO {
        val userByUserName = userDetailsRegistrationDTO.userName?.let { userRepo.findByUserName(it) }
        if (userByUserName != null) {
            if (userByUserName.isPresent) {
                throw DuplicatedUsernameException("this Username registered before!")
            } else {
                val userDetails = userMapper.userDetailsRegistrationDTOToUserDetails(userDetailsRegistrationDTO)
                userRepo.save(userDetails)
                return userMapper.userDetailsToUserDetailsDTO(userDetails)
            }
        } else {
            throw UsernameNullException("Username can't be empty!!")
        }
    }

    fun getUser(id: String): UserDetailsDTO {
        val userDetails = userRepo.findById(id)
        if (userDetails.isPresent)
            return userMapper.userDetailsToUserDetailsDTO(userDetails.get())
        else
            throw UserNotFoundException("User id not found")
    }

    fun getAllUsers(): List<UserDetailsDTO> {
        return userRepo.findAll().stream().map(userMapper::userDetailsToUserDetailsDTO).toList()
    }

    fun updateUser(id: String, userDetailsRegistrationDTO: UserDetailsRegistrationDTO): UserDetailsDTO {
        val userDetails = userRepo.findById(id)
        if (userDetails.isPresent) {
            if (userDetailsRegistrationDTO.userName?.let { userRepo.findByUserName(it).isPresent } == true) {
                throw DuplicatedUsernameException("this Username registered before!")
            } else {
                val updatedUserDetails = userDetails.get().copy(
                    userName = userDetailsRegistrationDTO.userName.let { it } ?: userDetails.get().userName,
                    name = userDetailsRegistrationDTO.name.let { it } ?: userDetails.get().name,
                    emailAddress = userDetailsRegistrationDTO.emailAddress.let { it } ?: userDetails.get().emailAddress,
                    hashPassword = userDetailsRegistrationDTO.password?.let { userMapper.hashedUserDetailsPassword(it) }
                        ?: userDetails.get().hashPassword
                )
                return userMapper.userDetailsToUserDetailsDTO(userRepo.save(updatedUserDetails))
            }
        } else
            throw UserNotFoundException("User id not found")
    }

    fun getUserByUsernameAndCheckedPassword(username: String, password: String): UserDetailsDTO {
        val userDetails = userRepo.findByUserName(username)
        if (userDetails.isPresent) {
            if (bCryptPasswordEncoder.matches(password, userDetails.get().hashPassword))
                return userMapper.userDetailsToUserDetailsDTO(userDetails.get())
            else
                throw UsernameOrPasswordIncorrectException("Username or Password incorrect")
        } else {
            throw UserNotFoundException("User id not found")
        }
    }
}