package com.shurdev.t_prep.domain.exceptions

sealed class ImportException : Exception()

class WrongStructureException : Exception()

class FileReadException : ImportException()