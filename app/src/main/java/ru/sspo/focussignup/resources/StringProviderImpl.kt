package ru.sspo.focussignup.resources

import android.content.Context
import ru.sspo.focussignup.R
import ru.sspo.focussignup.domain.StringProvider

class StringProviderImpl(
    private val context: Context
) : StringProvider {
    override fun getUsernameEmptyError(): String = context.getString(R.string.username_empty_error)

    override fun getUsernameLengthError(): String =
        context.getString(R.string.username_length_error)

    override fun getEmailFormatError(): String = context.getString(R.string.email_format_error)

    override fun getEmailLengthError(): String = context.getString(R.string.email_length_error)

    override fun getPasswordLengthError(): String =
        context.getString(R.string.password_length_error)

    override fun getPasswordMismatchError(): String =
        context.getString(R.string.password_mismatch_error)

    override fun getUserExistsError(): String = context.getString(R.string.user_exists_error)
}